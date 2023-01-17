import styled from 'styled-components';
import UserContentBox from '../organisms/user/UserContentBox';
import { WhiteShadowButton, BlackShadowButton } from '../atoms/Buttons';

const UserEdit = () => {
  return (
    <Container>
      <TabHeader> Change Info </TabHeader>
      <TabContentContainer>
        <UserInputContainer>
          <UserContentBox tag="NickName"> Input Your name </UserContentBox>
          <UserContentBox tag="Desc"> 내용을 입력하시오 </UserContentBox>
          <UserContentBox tag="Upload Profile"> Upload Profile </UserContentBox>
        </UserInputContainer>
        <ProfileImage />
      </TabContentContainer>
      <ButtonList>
        <WhiteShadowButton> Cancel </WhiteShadowButton>
        <BlackShadowButton> Submit </BlackShadowButton>
      </ButtonList>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
  height: 100vh;
  background-color: gray;

  & > div {
    width: 100%;
  }
`;

const TabHeader = styled.div`
  font-size: 36px;
  margin-top: 40px;
  margin-bottom: 30px;
`;
const UserInputContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 331px;
`;
const TabContentContainer = styled.div`
  display: flex;
  justify-content: space-between;
  padding-bottom: 81px;
`;

const ButtonList = styled.div`
  display: flex;
  justify-content: flex-end;

  & > * {
    margin-right: 9px;
  }
`;

const ProfileImage = styled.div`
  width: 193px;
  height: 193px;
  border-radius: 100%;
  background-color: yellow;
`;

export default UserEdit;
