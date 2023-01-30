import { useState, useEffect, useCallback } from 'react';
import styled from 'styled-components';
import TextArea from '../atoms/TextArea';
import { WhiteShadowButton, BlackShadowButton, ClearBlurButton } from '../atoms/Buttons';
import ShowcaseImageInput from '../molecules/showcase/ShowcaseImageInput';
import useShowcaseCreateStore from '../../store/showcaseCreateStore';

const SeriesCreatePage = () => {
  const [blur, setBlur] = useState(true);
  const { setTitle, setContent, initStore, postShowcase } = useShowcaseCreateStore();

  // save text to content state
  const handleOnChange = useCallback((event, setState) => {
    setState(event.target.value);
  });

  const handleToggleBlur = () => {
    setBlur(!blur);
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
          handleContent={e => handleOnChange(e, setTitle)}
        />
        <TextArea
          placeholder="설명을 입력하세요. (최대 150자)"
          height="80px"
          padding="none"
          maxLength="150"
          handleContent={e => handleOnChange(e, setContent)}
        />
        <InlineButtonContainer>
          <ClearBlurButton handleClick={handleToggleBlur} />
        </InlineButtonContainer>
        <FileInputContainer>
          <ShowcaseImageInput>최소 한장 이상의 사진을 업로드</ShowcaseImageInput>
        </FileInputContainer>
      </Container>
      <ButtonContainer>
        <WhiteShadowButton>Cancel</WhiteShadowButton>
        <BlackShadowButton onClick={postShowcase}>Submit</BlackShadowButton>
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
  width: 130px;
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
