import { Icon } from '@iconify/react';
import styled from 'styled-components';
import useUploadImage from '../../../hooks/useInputImage';
import { ParagraphSmall } from '../../../styles/typo';

const ShowcaseImageInput = () => {
  const { InputRef, imageSrc, handleInputOnChange } = useUploadImage();
  const handleImageUpload = () => {
    InputRef.current.click();
  };
  return (
    <>
      <input type="file" accept="image/*" style={{ display: 'none' }} ref={InputRef} onChange={handleInputOnChange} />
      <FileInputButton onClick={handleImageUpload}>
        {imageSrc !== '' ? (
          <PreviewImage src={imageSrc} alt="preview_image" />
        ) : (
          <>
            <Icon icon="mdi:image-plus-outline" style={{ fontSize: 40 }} />
            <FileInputLabel>최소 한장 이상의 사진을 업로드</FileInputLabel>
          </>
        )}
      </FileInputButton>
    </>
  );
};

const PreviewImage = styled.img`
  max-width: 100%;
  max-height: 100%;
`;

const FileInputButton = styled.button`
  width: 100%;
  height: 100%;
  border: none;
  display: flex;
  flex-direction: column;
  background-color: white;
  justify-content: center;
  align-items: center;
`;

const FileInputLabel = styled.div`
  ${ParagraphSmall};
  padding-top: 18px;
`;

export default ShowcaseImageInput;
