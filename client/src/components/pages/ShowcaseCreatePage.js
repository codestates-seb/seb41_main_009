import { useEffect } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

import TextArea from '../atoms/TextArea';
import Box from '../atoms/Box';
import { UserInfoSmall } from '../molecules/UserInfo';
import { WhiteShadowButton, BlackShadowButton } from '../atoms/Buttons';
import ShowcaseImageInput from '../molecules/showcase/ShowcaseImageInput';
import Dropdown from '../organisms/Dropdown';
import useContentCreateStore from '../../store/contentCreateStore';
import useGetUser from '../../hooks/useGetUser';
import useAuthStore from '../../store/useAuthStore';

const ShowcaseCreatePage = () => {
  const { setContent, initStore, postShowcase } = useContentCreateStore();
  const { currentUserId } = useAuthStore();
  const { userInfo } = useGetUser(currentUserId);
  const navigate = useNavigate();

  // save text to content state
  const handleTextOnChange = event => {
    setContent(event.target.value);
  };

  const handlePostShowcase = async () => {
    await postShowcase(() => {
      navigate('/');
      window.location.reload();
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
    <>
      <Container>
        <FileInputContainer>
          <DefaultBox>
            <ShowcaseImageInput>최소 한장 이상의 사진을 업로드</ShowcaseImageInput>
          </DefaultBox>
        </FileInputContainer>
        <ContentInputContiner>
          <DefaultBox>
            <UserInfoSmall id={userInfo?.id} name={userInfo?.nickname} image={userInfo?.imgUrl} />
          </DefaultBox>
          <DefaultBox>
            <TextArea placeholder="내용을 입력하세요(최대 300자)" maxLength="300" onChange={handleTextOnChange} />
          </DefaultBox>
          <Dropdown>Categorie</Dropdown>
        </ContentInputContiner>
      </Container>
      <ButtonContainer>
        <WhiteShadowButton onClick={handleCancle}>Cancel</WhiteShadowButton>
        <BlackShadowButton onClick={handlePostShowcase}>Submit</BlackShadowButton>
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
