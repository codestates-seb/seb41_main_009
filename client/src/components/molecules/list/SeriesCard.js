import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { AcrylicBase } from '../../atoms/AcrylicBase';
import { PostListStack } from './PostCard';
import { ParagraphMedium, LabelListTitle, LabelMedium } from '../../../styles/typo';
import { UserInfoSmall } from '../UserInfo';
import { PARAGRAPH } from '../../../constants/Paragraph';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 10px;
  width: var(--content-width);
  margin-bottom: 40px;
  background: url(https://unsplash.it/1920/1080/?random) rgba(45, 45, 45, 0.44);
  border-radius: 30px;
  overflow: hidden;
`;

const InfoLayer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 15px 0px;
  gap: 10px;

  width: fit-content;
  height: fit-content;
`;

const SeriesInfoLayer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0px 0px;

  width: fit-content;
  height: fit-content;
`;

const SeriesPostNumLayer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 5px;
  ${LabelMedium}
  color: #fff;

  width: fit-content;
  height: fit-content;

  flex: none;
  order: 1;
  align-self: stretch;
  flex-grow: 0;
`;

const ContextLayer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 5px 0px;
  gap: 10px;
  width: fit-content;
  height: fit-content;

  /* Inside auto layout */

  flex: none;
  order: 2;
  align-self: stretch;
  flex-grow: 0;
`;

const UserBox = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 10px;
  gap: 20px;

  width: fit-content;
  height: fit-content;
  color: #fff;
`;
const Title = styled.div`
  width: ${props => props.width || '326px'};
  height: fit-content;
  overflow: hidden;
  text-overflow: ellipsis;
  ${LabelListTitle}
  color: #fff;
  margin-bottom: 10px;
  &:hover {
    color: var(--gray-100);
  }
`;
const Paragraph = styled.div`
  width: ${props => props.width || '326px'};
  height: 42px;
  overflow: hidden;
  text-overflow: ellipsis;

  ${ParagraphMedium}
  color: #fff;
  &:hover {
    color: var(--gray-100);
  }
`;

// seriesdummy에 id 단위의 데이터들이 들어감

const SeriesCard = ({ width, series }) => {
  const { id, title, content, member, createdAt, modifiedAt, views, postId, totalPosts } = series;
  const { nickname, profileImageUrl } = member;

  return (
    <Container>
      <AcrylicBase>
        <InfoLayer>
          <SeriesInfoLayer>
            <Link to={`/series/category/${id}`}>
              <Title width={width}> {title || 'Series Title'} </Title>
              <SeriesPostNumLayer>
                <div>총 {totalPosts}개의 Post</div>
                <div>{views}번 조회</div>
              </SeriesPostNumLayer>
            </Link>
          </SeriesInfoLayer>
          <Paragraph width={width}>{content || PARAGRAPH}</Paragraph>
          <ContextLayer>
            <UserBox>
              <UserInfoSmall name={nickname} image={profileImageUrl} />
              <span>{(modifiedAt || createdAt).slice(0, 10)}</span>
            </UserBox>
          </ContextLayer>
        </InfoLayer>
        <PostListStack postId={postId} />
      </AcrylicBase>
    </Container>
  );
};

export default SeriesCard;
