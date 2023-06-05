import mediapipe as mp
from mediapipe.tasks import python
from mediapipe.tasks.python import vision
import os
import cv2
import numpy as np
import matplotlib.pyplot as plt
import math
import pymysql

conn = pymysql.connect(host='project-db-stu.smhrd.com',port=3307, user='campus_b_230519_3',password='smhrd3',db='campus_b_230519_3',charset='utf8')
model_path = "hair_segmenter.tflite"

# MySql에 저장된 애프터 사진 파일명 불러오기
import pymysql

member_id = 'bb'
salon_id = 'a000'
afterImg=''

sql = "select pic_path from my_history where member_id = %s and salon_id = %s"

with conn:
    with conn.cursor() as cur:
        cur.execute(sql,(member_id,salon_id))
        result = cur.fetchall()
        afterImg = result[-1][0]
        # print(afterImg)

# 이미지파일 경로

IMAGE_FOLDER = 'C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/afterImg'
IMAGE_FILENAMES = [afterImg]

for name in IMAGE_FILENAMES:
    image_path = os.path.join(IMAGE_FOLDER, name)+'.jpg'
    # print(image_path)

# Performs resizing and showing the image
image_path=image_path
images = {name: cv2.imread(image_path) for name in IMAGE_FILENAMES}
images.items()
# Preview the image(s)
# for name, image in images.items():
#     print(name)
#     resize_and_show(image)

BG_COLOR = (192, 192, 192) # gray
MASK_COLOR = (255, 255, 255) # white

# options
base_options = python.BaseOptions(model_asset_path=model_path)
options = vision.ImageSegmenterOptions(base_options=base_options,output_category_mask=True)

# 세그먼트 샘플링
# segmenter 생성
with vision.ImageSegmenter.create_from_options(options) as segmenter:

  # 이미지폴더를 순회하며 이미지파일을 읽어옴
    for name in IMAGE_FILENAMES:
        image_file_path = os.path.join(IMAGE_FOLDER, name)+'.jpg'

      # Create the MediaPipe image file that will be segmented
        image = mp.Image.create_from_file(image_file_path)

      # Retrieve the masks for the segmented image
        segmentation_result = segmenter.segment(image)
        category_mask = segmentation_result.category_mask
      
      # Generate solid color images for showing the output segmentation mask.
        image_data = image.numpy_view()
        fg_image = np.zeros(image_data.shape, dtype=np.uint8)
        fg_image[:] = MASK_COLOR
        bg_image = np.zeros(image_data.shape, dtype=np.uint8)
        bg_image[:] = BG_COLOR

        condition = np.stack((category_mask.numpy_view(),) * 3, axis=-1) > 0.2
        output_image = np.where(condition, fg_image, bg_image)

        # resize_and_show(output_image)

# 헤어 세그먼트(머리카락만 추출)
cnt=10

with vision.ImageSegmenter.create_from_options(options) as segmenter:
       
  # Loop through demo image(s)
    for name in IMAGE_FILENAMES:
        image_file_path = os.path.join(IMAGE_FOLDER, name)

      # Create the MediaPipe image file that will be segmented
        image = mp.Image.create_from_file(image_file_path+'.jpg')

      # Retrieve the masks for the segmented image
        segmentation_result = segmenter.segment(image)
        category_mask = segmentation_result.category_mask
      
        image_data = cv2.cvtColor(image.numpy_view(), cv2.COLOR_BGR2RGB)
      # category_mask 를 넘파이 배열로 변환
        category_mask = category_mask.numpy_view()
      
      # Apply hair mask to the original image
        output_image = cv2.bitwise_and(image_data, image_data, mask=category_mask)
      
      # Create a mask for the black background
        black_bg_mask = np.all(output_image == [0, 0, 0], axis=2)

      # Create an alpha channel with the black background mask
        alpha_channel = np.where(black_bg_mask, 0, 255).astype(np.uint8)

      # Add the alpha channel to the hair image
        hair_image_with_alpha = cv2.cvtColor(output_image, cv2.COLOR_BGR2BGRA)
        hair_image_with_alpha[:, :, 3] = alpha_channel

      # 이미지 저장
        cnt = cnt+1
        num = str(cnt)
        output_path = 'C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/hairStyle/hairStyle_'+salon_id+'_'+num+'.jpg'
        cv2.imwrite(output_path, hair_image_with_alpha)
    
      # Show the image with hair segmentation
        # resize_and_show(hair_image_with_alpha)





