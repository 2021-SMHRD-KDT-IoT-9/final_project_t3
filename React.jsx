//before
const handleSubmit = async (e) => {
    console.log('보낸 텍스트 :', text)

    e.preventDefault();
    try {
      // 라즈베리파이에 HTTP 요청 보내기
      const reponse = await axios.post('http://172.30.1.91:5000/execute2', { text });

    } catch (error) {
      console.error('사진 캡처 중 오류 발생:', error);
    }

    navigate('/after-service');
  };

  //after
  const handleSubmit2 = async (e) => {
    console.log('보낸 텍스트 :', text)

    e.preventDefault();
    try {
      // 라즈베리파이에 HTTP 요청 보내기
      const reponse = await axios.post('http://172.30.1.91:5000/execute2', { text });

    } catch (error) {
      console.error('사진 캡처 중 오류 발생:', error);
    }

    navigate('/after-service');
  };