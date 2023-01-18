import { useParams } from 'react-router-dom';
import styled from 'styled-components';
import { ACTIVITIES, STATS } from '../../constants/UserPageDataLists';
import useGetUser from '../../hooks/useGetUser';
import useGetUserActivities from '../../hooks/useGetUserActivities';
import { SplashStickerLabelDefault } from '../molecules/stickerLabel/SplashStickerLabel';
import { UserInfo } from '../molecules/UserInfo';
import UserContentBox from '../organisms/user/UserContentBox';

const User = () => {
  const params = useParams('id');
  const { userId } = params;
  const { userInfo, isLoadingUser, isLoadingUserError } = useGetUser(userId);
  const { userActivities, isLoadingActivities, isLoadingActivitiesError } = useGetUserActivities(userId, 1);

  console.log(userActivities);
  console.log(isLoadingUser, isLoadingUserError);
  console.log(isLoadingActivities, isLoadingActivitiesError);

  return (
    <Container>
      <UserInfo />
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
        {ACTIVITIES.map((activity, idx) => {
          const activityTitle = activity.slice(0, 1).toUpperCase() + activity.slice(1);
          return (
            <UserContentBox key={activity} tag={activityTitle}>
              {userActivities[idx]?.map(content => {
                return <CardContainer>{content?.content}</CardContainer>;
              })}
            </UserContentBox>
          );
        })}
      </TabContentContainer>
      <SplashStickerLabelDefault />
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

const CardContainer = styled.div`
  box-sizing: border-box;
  width: ${props => (props.width ? props.width : '331px')};
  height: ${props => (props.height ? props.height : '76px')};
  padding: 28px 20px;
  gap: 10px;
  border: 2px solid black;
  box-shadow: var(--boxShadow-02) black;
`;

export default User;
