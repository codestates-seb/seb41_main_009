import styled from 'styled-components';
import { useState } from 'react';
import CardContainer from '../../atoms/CardContainer';
import { TextButton } from '../../atoms/Buttons';
import { LabelSmall, LabelXSmall } from '../../../styles/typo';
import UserContentBox from './UserContentBox';
import useGetUserActivity from '../../../hooks/useGetUserActivity';

const UserActivitiesBox = ({ activity, id }) => {
  const [page, setPage] = useState(1);
  const { userContents, totalPages } = useGetUserActivity(activity, id, page);
  const activityTitle = activity.slice(0, 1).toUpperCase() + activity.slice(1);

  return (
    <UserContentBox key={activity} tag={activityTitle}>
      <CardContainer>
        {userContents?.map(content => {
          return (
            <ActivityButton to="" key={content.id}>
              {content.title ? content.title : content.content}
            </ActivityButton>
          );
        })}
        {totalPages || page < totalPages ? <MoreButton onClick={() => setPage(page + 1)}>더보기</MoreButton> : null}
      </CardContainer>
    </UserContentBox>
  );
};

const ActivityButton = styled(TextButton)`
  ${LabelSmall}
  display: block;
  width: 100%;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
`;

const MoreButton = styled(TextButton)`
  display: flex;
  justify-content: flex-end;
  width: 100%;
  ${LabelXSmall};
`;
export default UserActivitiesBox;
