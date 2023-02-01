import { useState, useCallback, useRef, useEffect } from 'react';
import { UPLOAD_SIZE_EXCEEDED } from '../constants/Messages';
import useShowcaseCreateStore from '../store/showcaseCreateStore';
import config from '../constants/config';

const useInputImage = () => {
  const InputRef = useRef(null);
  const [file, setFile] = useState(null);
  const [errorMsg, setErrorMsg] = useState('');
  const { imageBase64, setImageBase64, setImageBinary, setFileInfos } = useShowcaseCreateStore();
  const { MAX_UPLOAD_SIZE } = config;

  // 이미지 프리뷰를 위한 dataURL 저장
  const encodeFileToBase64 = useCallback(inputFile => {
    const reader = new FileReader();
    reader.readAsDataURL(inputFile);
    return new Promise(res => {
      reader.onload = () => {
        setImageBase64(reader.result);
        console.log(reader.result);
        res();
      };
    });
  });

  const convertFileToBinary = inputFile => {
    const reader = new FileReader();
    reader.readAsArrayBuffer(inputFile);
    return new Promise(res => {
      reader.onloadend = () => {
        setImageBinary(new Uint8Array(reader.result));
        res();
      };
    });
  };

  const handleInputOnChange = useCallback(async event => {
    const imageFile = event.target.files[0];

    // guard clause
    if (!imageFile) return;

    // check file size
    if (MAX_UPLOAD_SIZE < imageFile.size) {
      setErrorMsg(UPLOAD_SIZE_EXCEEDED);
      return;
    }

    await convertFileToBinary(imageFile);
    await encodeFileToBase64(imageFile).then(() => {
      setFileInfos([
        {
          index: 1,
          size: imageFile.size,
          contentType: imageFile.type.split('/')[1],
        },
      ]);
      setFile(imageFile);
    });
  });

  useEffect(() => {
    // hook 이 언마운트 될때 전역저장소에서 이미지소스 지우기
    return () => {
      setImageBase64('');
    };
  }, []);

  return { InputRef, file, imageBase64, errorMsg, handleInputOnChange };
};

export default useInputImage;
