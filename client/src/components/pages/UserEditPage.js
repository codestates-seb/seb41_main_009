import styled from 'styled-components';
import UserContentBox from '../organisms/user/UserContentBox';
import { WhiteShadowButton, BlackShadowButton } from '../atoms/Buttons';
import { DisplayMedium } from '../../styles/typo';
import InputCard from '../molecules/InputCard';

const UserEdit = () => {
  return (
    <Container>
      <TabHeader> Change Info </TabHeader>
      <TabContentContainer>
        <UserInputContainer>
          <UserContentBox tag="NickName">
            <InputCard placeholder="변경하실 닉네임을 입력해 주세요." inputWidth="100%" />
          </UserContentBox>
          <UserContentBox tag="Description">
            <InputCard placeholder="내 소개를 입력해 주세요." inputWidth="100%" />
          </UserContentBox>
          <UserContentBox tag="Upload Profile">
            <InputCard type="file" inputWidth="100%" />
          </UserContentBox>
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

  & > div {
    width: 100%;
  }
`;

const TabHeader = styled.div`
  ${DisplayMedium}
  margin: 40px 0;
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
