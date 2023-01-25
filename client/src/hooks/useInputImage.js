import { useState, useCallback, useRef } from 'react';
import { UPLOAD_SIZE_EXCEEDED } from '../constants/Messages';
import config from '../config';

const useInputImage = () => {
  const InputRef = useRef(null);
  const [file, setFile] = useState(null);
  const [imgSrc, setImgScr] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const { MAX_UPLOAD_SIZE } = config;

  // encode file to base64
  const encodeFileToBase64 = useCallback(blob => {
    const reader = new FileReader();
    reader.readAsDataURL(blob);
    return new Promise(res => {
      reader.onload = () => {
        setImgScr(reader.result);
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

    setFile(imageFile);
    await encodeFileToBase64(imageFile);
  });

  return { InputRef, file, imgSrc, errorMsg, handleInputOnChange };
};

export default useInputImage;
