import { useParams } from 'react-router-dom';
import styled from 'styled-components';
import { ACTIVITIES, STATS } from '../../constants/UserPageDataLists';
import useGetUser from '../../hooks/useGetUser';
import CardContainer from '../atoms/CardContainer';
import Loading from '../atoms/Loading';
import { SplashStickerLabelDefault } from '../molecules/stickerLabel/SplashStickerLabel';
import { UserInfo } from '../molecules/UserInfo';
import UserActivitiesBox from '../organisms/user/UserActivitiesBox';
import UserContentBox from '../organisms/user/UserContentBox';

const User = () => {
  const params = useParams('id');
  const { userId } = params;
  const { userInfo, isLoadingUser } = useGetUser(userId);

  return (
    <Container>
      {isLoadingUser ? (
        <Loading />
      ) : (
        <>
          <UserInfo
            id={userInfo.id}
            name={userInfo.nickname}
            introduction={userInfo.introduction}
            image={userInfo.profileUrl}
          />
          <TabHeader> Stats </TabHeader>
          <TabContentContainer>
            {STATS.map(stat => {
              const [displayName, name] = stat;
              return (
                <UserContentBox key={name} tag={displayName}>
                  <CardContainer>{userInfo[name]}</CardContainer>
                </UserContentBox>
              );
            })}
          </TabContentContainer>
          <TabHeader> Activities </TabHeader>
          <TabContentContainer>
            {ACTIVITIES.map(activity => {
              return <UserActivitiesBox key={activity} activity={activity} id={userId} />;
            })}
          </TabContentContainer>
          <SplashStickerLabelDefault
            boyMessage={userInfo.nickname}
            girlMessage={userInfo.nickname}
            emailMessage={userInfo.email}
            createdAtMessage={new Date().toDateString(userInfo.createdAt)}
          />
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
  /* height: 100vh; */

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
  margin-bottom: 40px;
`;

export default User;
