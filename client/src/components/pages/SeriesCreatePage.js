import { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

import TextArea from '../atoms/TextArea';
import Dropdown from '../organisms/Dropdown';
import { WhiteShadowButton, BlackShadowButton, ClearBlurButton } from '../atoms/Buttons';
import ShowcaseImageInput from '../molecules/showcase/ShowcaseImageInput';
import useContentCreateStore from '../../store/contentCreateStore';

const SeriesCreatePage = () => {
  const [blur, setBlur] = useState(true);
  const { setTitle, setContent, initStore, postSeries } = useContentCreateStore();
  const navigate = useNavigate();

  const handleOnChangeTitle = event => {
    setTitle(event.target.value);
  };
  const handleOnChangeContent = event => {
    setContent(event.target.value);
  };

  const handleToggleBlur = () => {
    setBlur(!blur);
  };

  const handlePostSeries = () => {
    postSeries(() => {
      navigate(-1);
    });
  };

  const handleCancle = () => {
    navigate(-1);
  };

  useEffect(() => {
    return () => {
      initStore();
    };
  }, []);

  return (
    <BackgroundWrapper>
      <Container blur={blur}>
        <TextArea
          placeholder="타이틀을 입력해주세요."
          height="30px"
          maxLength="50"
          padding="none"
          onChange={handleOnChangeTitle}
        />
        <TextArea
          placeholder="설명을 입력하세요. (최대 150자)"
          height="80px"
          padding="none"
          maxLength="150"
          onChange={handleOnChangeContent}
        />
        <InlineButtonContainer>
          <ClearBlurButton handleClick={handleToggleBlur} />
          <DropdownContainer>
            <Dropdown padding="10px">Categorie</Dropdown>
          </DropdownContainer>
        </InlineButtonContainer>
        <FileInputContainer>
          <ShowcaseImageInput>최소 한장 이상의 사진을 업로드</ShowcaseImageInput>
        </FileInputContainer>
      </Container>
      <ButtonContainer>
        <WhiteShadowButton onClick={handleCancle}>Cancel</WhiteShadowButton>
        <BlackShadowButton onClick={handlePostSeries}>Submit</BlackShadowButton>
      </ButtonContainer>
    </BackgroundWrapper>
  );
};

const BackgroundWrapper = styled.div`
  width: 100%;
  height: 715px;
  border-radius: 30px;
  background: url(https://unsplash.it/1920/1080/?random);
`;

const DropdownContainer = styled.div`
  width: 150px;
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  padding: 100px 80px;
  width: 100%;
  gap: 32px;
  height: 715px;
  border: 1px solid rgba(0, 0, 0, 0);
  border-radius: 30px;
  transition: all 100ms ease-out;
  backdrop-filter: ${props => (props.blur ? 'blur(20px) brightness(70%)' : 'none')};
  box-sizing: border-box;

  & > textarea {
    overflow-y: hidden;
    color: white;
    background-color: rgba(256, 256, 256, 0.3);

    &::placeholder {
      color: white;
      font-weight: 300;
    }
  }
`;

const InlineButtonContainer = styled.div`
  padding-top: 50px;
  display: flex;
  align-items: center;
  gap: 10px;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  gap: 38px;
  margin-top: 24px;
`;

const FileInputContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 488px;
  height: 242px;
`;

export default SeriesCreatePage;
