import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import { useState } from 'react';
import UserContentBox from '../organisms/user/UserContentBox';
import { WhiteShadowButton, BlackShadowButton } from '../atoms/Buttons';
import { DisplayMedium } from '../../styles/typo';
import InputCard from '../molecules/InputCard';
import useGetUser from '../../hooks/useGetUser';
import UserImage from '../atoms/UserImage';
import { IMAGESIZELIMIT } from '../../constants/Messages';

const UserEdit = () => {
  const params = useParams('id');
  const { userId } = params;
  const { userInfo, isLoadingUser, isLoadingUserError } = useGetUser(userId);
  const { nickname, imgUrl, introduction } = userInfo;
  const [newNickname, setNewNickname] = useState(nickname);
  const [newImage, setNewImage] = useState(imgUrl);
  const [newIntroduction, setNewIntroduction] = useState(introduction);
  const MAXIMAGESIZE = 2097152;

  console.log(isLoadingUser, isLoadingUserError);

  const uploadNewImage = e => {
    const image = e.currentTarget.files[0];

    if (image && image.size <= MAXIMAGESIZE) {
      const imagePath = URL.createObjectURL(image);
      setNewImage(imagePath);
    } else {
      alert(IMAGESIZELIMIT);
      setNewImage(imgUrl);
    }
  };

  const changeNewNickname = e => {
    setNewNickname(e.target.value);
  };

  const changeNewIntroduction = e => {
    setNewIntroduction(e.target.value);
  };

  const submitNewUserInfo = () => {
    if (newNickname && newIntroduction && newImage) {
      //
    }
  };

  return (
    <Container>
      <TabHeader> Change Info </TabHeader>
      <TabContentContainer>
        <UserInputContainer>
          <UserContentBox tag="Nickname">
            <InputCard
              placeholder="변경하실 닉네임을 입력해 주세요."
              inputWidth="100%"
              defaultValue={nickname}
              onChange={changeNewNickname}
            />
          </UserContentBox>
          <UserContentBox tag="Description">
            <InputCard
              placeholder="내 소개를 입력해 주세요."
              inputWidth="100%"
              defaultValue={introduction}
              onChange={changeNewIntroduction}
            />
          </UserContentBox>
          <UserContentBox tag="Upload Profile">
            <InputCard type="file" accept="image/*" inputWidth="100%" onChange={uploadNewImage} />
          </UserContentBox>
        </UserInputContainer>
        <UserImage src={newImage} sizes="193px" />
      </TabContentContainer>
      <ButtonList>
        <WhiteShadowButton> Cancel </WhiteShadowButton>
        <BlackShadowButton onClick={submitNewUserInfo}> Submit </BlackShadowButton>
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

export default UserEdit;
