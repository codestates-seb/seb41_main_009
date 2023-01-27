import { useState, useCallback, useRef, useEffect } from 'react';
import { UPLOAD_SIZE_EXCEEDED } from '../constants/Messages';
import useShowcaseCreateStore from '../store/showcaseCreateStore';
import config from '../constants/config';

const useInputImage = () => {
  const InputRef = useRef(null);
  const [file, setFile] = useState(null);
  const [errorMsg, setErrorMsg] = useState('');
  const { imageSrc, setImageSrc, setFileInfos } = useShowcaseCreateStore();
  const { MAX_UPLOAD_SIZE } = config;

  // encode file to base64
  const encodeFileToBase64 = useCallback(blob => {
    const reader = new FileReader();
    reader.readAsDataURL(blob);
    return new Promise(res => {
      reader.onload = () => {
        setImageSrc(reader.result);
        res();
      };
    });
  });

  const handleInputOnChange = useCallback(async event => {
    const imageFile = event.target.files[0];

    // guard clause
    if (!imageFile) return;

    // check file size
    if (MAX_UPLOAD_SIZE < imageFile.size) {
      setErrorMsg(UPLOAD_SIZE_EXCEEDED);
      return;
    }

    await encodeFileToBase64(imageFile).then(() => {
      setFileInfos([
        {
          index: 1,
          size: imageFile.size,
          type: imageFile.type.split('/')[1],
        },
      ]);
      setFile(imageFile);
    });
  });

  useEffect(() => {
    // hook 이 언마운트 될때 전역저장소에서 이미지소스 지우기
    return () => {
      setImageSrc('');
    };
  }, []);

  return { InputRef, file, imageSrc, errorMsg, handleInputOnChange };
};

export default useInputImage;
