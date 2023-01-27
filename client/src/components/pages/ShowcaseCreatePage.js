import { useEffect, useCallback } from 'react';
import styled from 'styled-components';
import TextArea from '../atoms/TextArea';
import Box from '../atoms/Box';
import { UserInfoSmall } from '../molecules/UserInfo';
import { WhiteShadowButton, BlackShadowButton } from '../atoms/Buttons';
import ShowcaseImageInput from '../molecules/showcase/ShowcaseImageInput';
import Dropdown from '../organisms/Dropdown';
import useShowcaseCreateStore from '../../store/showcaseCreateStore';

const ShowcaseCreatePage = () => {
  const { setContent, initStore, postShowcase } = useShowcaseCreateStore();

  // save text to content state
  const handleTextOnChange = useCallback(event => {
    setContent(event.target.value);
  });

  useEffect(() => {
    return () => {
      initStore();
    };
  }, []);

  return (
    <>
      <Container>
        <FileInputContainer>
          <DefaultBox>
            <ShowcaseImageInput>최소 한장 이상의 사진을 업로드</ShowcaseImageInput>
          </DefaultBox>
        </FileInputContainer>
        <ContentInputContiner>
          <DefaultBox>
            <UserInfoSmall />
          </DefaultBox>
          <DefaultBox>
            <TextArea placeholder="내용을 입력하세요(최대 300자)" maxLength="300" handleContent={handleTextOnChange} />
          </DefaultBox>
          <Dropdown>Categorie</Dropdown>
        </ContentInputContiner>
      </Container>
      <ButtonContainer>
        <WhiteShadowButton>Cancel</WhiteShadowButton>
        <BlackShadowButton onClick={postShowcase}>Submit</BlackShadowButton>
      </ButtonContainer>
    </>
  );
};

const Container = styled.div`
  display: flex;
  width: 100%;
  gap: 32px;
  height: 631px;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  gap: 38px;
  margin-top: 24px;
`;

const FileInputContainer = styled.div`
  display: flex;
  width: 693px;
`;

const ContentInputContiner = styled.div`
  display: flex;
  flex-direction: column;
  width: 331px;
  gap: 17px;

  div {
    &:nth-child(2) {
      margin-bottom: 33px;
    }
  }
`;

const DefaultBox = styled(Box).attrs({
  border: '3px solid black',
  boxShadow: '8px 8px 0px #000000;',
})``;

export default ShowcaseCreatePage;
