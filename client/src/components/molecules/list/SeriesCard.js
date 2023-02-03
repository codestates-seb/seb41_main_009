import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { AcrylicBase } from '../../atoms/AcrylicBase';
import { PostListStack } from './PostCard';
import { ParagraphMedium, LabelListTitle, LabelMedium } from '../../../styles/typo';
import { UserInfoSmall } from '../UserInfo';
import { PARAGRAPH } from '../../../constants/Paragraph';
import NoPost from './NoPost';

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

  > a {
    text-decoration: none;
  }
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
    color: var(--gray-500);
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
    color: var(--gray-500);
  }
`;

// seriesdummy에 id 단위의 데이터들이 들어감

// 나중에 Title,Paragraph조건문을 제거했을 때 렌더링 속도가 어떻게 변하는지 확인해봐야함
const SeriesCard = ({ width, series }) => {
  const { id, title, content, member, createdAt, modifiedAt, views, post, totalPosts } = series;
  const { nickname, profileUrl } = member;

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
              <UserInfoSmall name={nickname} image={profileUrl} id={member.id} />
              <span>{(modifiedAt || createdAt).slice(0, 10)}</span>
            </UserBox>
          </ContextLayer>
        </InfoLayer>
        {post ? <PostListStack post={post} /> : <NoPost />}
      </AcrylicBase>
    </Container>
  );
};

export default SeriesCard;
