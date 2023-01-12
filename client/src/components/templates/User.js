import styled from 'styled-components';
import UserContentBox from '../organisms/user/UserContentBox';
import UserTitle from '../organisms/user/UserTitle';

const User = () => {
  return (
    <Container>
      <UserTitle />
      <TabHeader> Stats </TabHeader>
      <TabContentContainer>
        <UserContentBox tag="NickName"> dddd </UserContentBox>
        <UserContentBox tag="Email"> test1@test.com </UserContentBox>
        <UserContentBox tag="Created At"> 2022-01-10 </UserContentBox>
      </TabContentContainer>
      <TabHeader> Activities </TabHeader>
      <TabContentContainer>
        <UserContentBox tag="Series"> DDDDDDDDDDDDDD </UserContentBox>
        <UserContentBox tag="Post"> ddddddddd </UserContentBox>
        <UserContentBox tag="Showcase"> dddddddddd </UserContentBox>
      </TabContentContainer>
      <ImageFooter />
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

const TabContentContainer = styled.div`
  display: flex;
`;

const ImageFooter = styled.div`
  margin-top: 76px;
  background-color: blue;
  height: 260px;
`;

export default User;
