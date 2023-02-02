import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import UserContentBox from '../organisms/user/UserContentBox';
import { WhiteShadowButton, BlackShadowButton } from '../atoms/Buttons';
import { DisplayMedium } from '../../styles/typo';
import InputCard from '../molecules/InputCard';
import useGetUser from '../../hooks/useGetUser';
import UserImage from '../atoms/UserImage';
import { IMAGE_SIZE_LIMIT, INVALID_DESCRIPTION, INVALID_NICKNAME } from '../../constants/Messages';
import { isValidIntroduction, isValidNickname } from '../../functions/isValid';
import Loading from '../atoms/Loading';
import uploadNewUserInfo from '../../functions/uploadNewUserInfo';
import getSignedUrl from '../../functions/getSignedUrl';

const UserEdit = () => {
  const params = useParams('id');
  const { userId } = params;
  const { userInfo, isLoadingUser } = useGetUser(userId);
  const { nickname, imgUrl, introduction } = userInfo;
  const [newNickname, setNewNickname] = useState('');
  const [newImage, setNewImage] = useState('');
  const [newDescription, setNewDescription] = useState('');
  const [nicknameMessage, setNicknameMessage] = useState('');
  const [descriptionMessage, setDescriptionMessage] = useState('');
  const MAXIMAGESIZE = 2097152;

  useEffect(() => {
    setNewNickname(nickname);
    setNewDescription(introduction);
    setNewImage(imgUrl);
  }, [userInfo]);

  const uploadNewImage = async e => {
    const image = e.currentTarget.files[0];
    const { size, type } = image;

    if (image && size <= MAXIMAGESIZE) {
      const { fileURL, signedURL } = await getSignedUrl('members', size, type);

      const base64Image = await new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(image);
        reader.onload = event => resolve(event.target.result);
        reader.onerror = error => reject(error);
      });

      axios
        .put(signedURL, base64Image, {
          headers: {
            'Content-Type': type,
            Authorization: null,
          },
          withCredentials: false,
        })
        .then(() => {
          setNewImage(fileURL);
        })
        .catch(err => console.log(err));
    } else {
      alert(IMAGE_SIZE_LIMIT);
    }
  };

  const changeNewNickname = e => {
    const nicknameValue = e.target.value;

    if (!nicknameValue || !isValidNickname(nicknameValue)) {
      setNicknameMessage(INVALID_NICKNAME);
    } else {
      setNicknameMessage('');
      setNewNickname(nicknameValue);
    }
  };

  const changeNewIntroduction = e => {
    const descriptionValue = e.target.value;

    if (!isValidIntroduction(descriptionValue)) {
      setDescriptionMessage(INVALID_DESCRIPTION);
    } else {
      setDescriptionMessage('');
      setNewDescription(descriptionValue);
    }
  };

  const submitNewUserInfo = () => {
    if (!nicknameMessage && !descriptionMessage && newImage) {
      uploadNewUserInfo(newNickname, newDescription, newImage, userId);
    }
  };

  return (
    <Container>
      {isLoadingUser ? (
        <Loading />
      ) : (
        <>
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
        </>
      )}
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
