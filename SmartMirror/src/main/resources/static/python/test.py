from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/testpage', methods=['POST'])
def testpage():
    data = request.get_json()  # JSON 데이터를 받아옴

    member_id = data['member_id']  # 'member_id' 키의 값 추출
    salon_id = data['salon_id']  # 'salon_id' 키의 값 추출

    # 받은 데이터를 처리하는 로직을 작성
    # ...

    # 결과를 JSON 형식으로 반환
    response_data = {
        'result': 'success',
        'message': 'JSON received and processed successfully',
        'member_id': member_id,
        'salon_id': salon_id
    }
    return jsonify(response_data)

if __name__ == '__main__':
    app.run(host='59.0.234.211/testp', port=5000)