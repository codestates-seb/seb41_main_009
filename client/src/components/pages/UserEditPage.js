import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import { useState } from 'react';
import UserContentBox from '../organisms/user/UserContentBox';
import { WhiteShadowButton, BlackShadowButton } from '../atoms/Buttons';
import { DisplayMedium } from '../../styles/typo';
import InputCard from '../molecules/InputCard';
import useGetUser from '../../hooks/useGetUser';
import UserImage from '../atoms/UserImage';
import { IMAGESIZELIMIT, INVALIDDESCRIPTION, INVALIDNICKNAME } from '../../constants/Messages';
import { isValidIntroduction, isValidNickname } from '../../functions/isValid';

const UserEdit = () => {
  const params = useParams('id');
  const { userId } = params;
  const { userInfo, isLoadingUser, isLoadingUserError } = useGetUser(userId);
  const { nickname, imgUrl, introduction } = userInfo;
  const [newNickname, setNewNickname] = useState(nickname);
  const [newImage, setNewImage] = useState(imgUrl);
  const [newDescription, setNewDescription] = useState(introduction);
  const [nicknameMessage, setNicknameMessage] = useState('');
  const [descriptionMessage, setDescriptionMessage] = useState('');
  const MAXIMAGESIZE = 2097152;

  console.log(isLoadingUser, isLoadingUserError);

  const uploadNewImage = e => {
    const image = e.currentTarget.files[0];

    if (image && image.size <= MAXIMAGESIZE) {
      const imagePath = URL.createObjectURL(image);
      // 이미지 처리 로직에 따라서 여기서 이미지 서버에 업로드할수도 있음
      console.log(image);
      setNewImage(imagePath);
    } else {
      alert(IMAGESIZELIMIT);
      setNewImage(imgUrl);
    }
  };

  const changeNewNickname = e => {
    const nicknameValue = e.target.value;

    if (!nicknameValue || !isValidNickname(nicknameValue)) {
      setNicknameMessage(INVALIDNICKNAME);
    } else {
      setNicknameMessage('');
      setNewNickname(nicknameValue);
    }
  };

  const changeNewIntroduction = e => {
    const descriptionValue = e.target.value;

    if (!isValidIntroduction(descriptionValue)) {
      setDescriptionMessage(INVALIDDESCRIPTION);
    } else {
      setDescriptionMessage('');
      setNewDescription(descriptionValue);
    }
  };

  const submitNewUserInfo = () => {
    if (!nicknameMessage && !descriptionMessage && newImage) {
      // update 로직에 따라서 함수 추후 구현 예정
      console.log(newNickname, newDescription, newImage);
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
              messageColor="red"
              message={nicknameMessage}
            />
          </UserContentBox>
          <UserContentBox tag="Description">
            <InputCard
              placeholder="내 소개를 입력해 주세요."
              inputWidth="100%"
              defaultValue={introduction}
              onChange={changeNewIntroduction}
              messageColor="red"
              message={descriptionMessage}
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
