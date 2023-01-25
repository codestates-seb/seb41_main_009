import { useState, useCallback, useEffect } from 'react';
import styled from 'styled-components';
import Box from '../atoms/Box';
import { ParagraphMedium } from '../../styles/typo';
import { UserInfoSmall } from '../molecules/UserInfo';
import { WhiteShadowButton, BlackShadowButton } from '../atoms/Buttons';
import ShowcaseImageInput from '../molecules/showcase/ShowcaseImageInput';
import Dropdown from '../organisms/Dropdown';

const ShowcaseCreatePage = () => {
  const [content, setContent] = useState('');

  // save text to content state
  const handleTextOnChange = useCallback(event => {
    setContent(event.target.value);
    console.log(content);
  });

  useEffect(() => {
    console.log('mount test');
  });

  return (
    <>
      <Container>
        <FileInputContainer>
          <DefaultBox>
            <ShowcaseImageInput />
          </DefaultBox>
        </FileInputContainer>
        <ContentInputContiner>
          <DefaultBox>
            <UserInfoSmall />
          </DefaultBox>
          <DefaultBox>
            <ContentTextArea placeholder="내용을 입력하세요(최대 300자)" onChange={handleTextOnChange} />
          </DefaultBox>
          <Dropdown>Categorie</Dropdown>
        </ContentInputContiner>
      </Container>
      <ButtonContainer>
        <WhiteShadowButton>Cancel</WhiteShadowButton>
        <BlackShadowButton>Submit</BlackShadowButton>
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

const ContentTextArea = styled.textarea`
  ${ParagraphMedium};
  padding: 0px 0px 100px 0px;
  height: 310px;
  border: none;
  outline-color: white;
`;

const DefaultBox = styled(Box).attrs({
  border: '3px solid black',
  boxShadow: '8px 8px 0px #000000;',
})``;

export default ShowcaseCreatePage;
