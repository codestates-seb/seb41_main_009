import { useParams } from 'react-router-dom';
import styled from 'styled-components';
import { ACTIVITIES, STATS } from '../../constants/UserPageDataLists';
import useGetUser from '../../hooks/useGetUser';
import useGetUserActivities from '../../hooks/useGetUserActivities';
import { LabelSmall } from '../../styles/typo';
import { TextButton } from '../atoms/Buttons';
import CardContainer from '../atoms/CardContainer';
import { SplashStickerLabelDefault } from '../molecules/stickerLabel/SplashStickerLabel';
import { UserInfo } from '../molecules/UserInfo';
import UserContentBox from '../organisms/user/UserContentBox';

const User = () => {
  const params = useParams('id');
  const { userId } = params;
  const { userInfo, isLoadingUser, isLoadingUserError } = useGetUser(userId);
  const { userActivities, isLoadingActivities, isLoadingActivitiesError } = useGetUserActivities(userId, 1);

  console.log(isLoadingUser, isLoadingUserError);
  console.log(isLoadingActivities, isLoadingActivitiesError);

  return (
    <Container>
      <UserInfo
        id={userInfo.id}
        name={userInfo.nickname}
        introduction={userInfo.introduction}
        image={userInfo.imgUrl}
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
        {ACTIVITIES.map((activity, idx) => {
          const activityTitle = activity.slice(0, 1).toUpperCase() + activity.slice(1);
          return (
            <UserContentBox key={activity} tag={activityTitle}>
              <CardContainer>
                {userActivities[idx]
                  ?.map(content => {
                    return (
                      <ActivityButton to={`/${activity}/${content.id}`}>
                        {content.title ? content.title : content.content}
                      </ActivityButton>
                    );
                  })
                  .slice(0, 5)}
              </CardContainer>
            </UserContentBox>
          );
        })}
      </TabContentContainer>
      <SplashStickerLabelDefault
        boyMessage={userInfo.nickname}
        girlMessage={userInfo.nickname}
        emailMessage={userInfo.email}
        createdAtMessage={userInfo.createdAt}
      />
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

const ActivityButton = styled(TextButton)`
  ${LabelSmall}
`;

export default User;
