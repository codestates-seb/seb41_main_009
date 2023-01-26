import styled from 'styled-components';
import { useState } from 'react';
import { PostListStack, PostCard } from '../list/PostCard';
import { LabelListTitle, LabelMedium } from '../../../styles/typo';
import Pagination from '../Pagination';
import useGetSeriesPostList from '../../../hooks/useGetSeriesPostList';

const SeriesHeaderPostList = ({ seriesId, page }) => {
  const [isListOpen, setIsListOpen] = useState(false); // list 숨기기

  const { postList, postPageInfo } = useGetSeriesPostList(seriesId, page);

  const PostListToggle = () => {
    setIsListOpen(!isListOpen);
  };
  return (
    <Container>
      <InnerLayer>
        <UpperSection>
          <TextGroup>
            <Title> Series Name Post List</Title>
            <SeriesPostNumLayer>
              <p>All Post</p>
              <p> {postPageInfo.totalPage} 개</p>
            </SeriesPostNumLayer>
          </TextGroup>
          {isListOpen ? <PostListStack postId={postList[0].id} /> : ''}
        </UpperSection>
        <LowerSection>
          {isListOpen ? (
            ''
          ) : (
            <PostListSection>
              {postList && postList.length > 0
                ? postList.map(post => {
                    return <PostCard key={post.id} postId={post.id} />;
                  })
                : ''}
              <Pagination totalPages={Number(postPageInfo.totalPage)} />
            </PostListSection>
          )}
          <button type="button" onClick={PostListToggle}>
            자세히 보기
          </button>
        </LowerSection>
      </InnerLayer>
    </Container>
  );
};

export default SeriesHeaderPostList;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 0px;

  position: relative;
  width: 90%;
  height: fit-content;

  background-color: rgba(84, 84, 84, 0.5);
  border-radius: 30px;
  overflow: hidden;
`;
const InnerLayer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 32px;
  gap: 32px;

  width: inherit;
  height: fit-content;
  overflow: hidden;
  text-overflow: ellipsis;
  ${LabelListTitle}
  color: var(--gray-700);
  &:hover {
    color: var(--gray-100);
  }
`;
const UpperSection = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-start;
  padding: 0px;

  width: fit-content;
  height: fit-content;
  overflow: hidden;
  text-overflow: ellipsis;
  ${LabelListTitle}
  color: var(--gray-700);
  &:hover {
    color: var(--gray-100);
  }
`;

const LowerSection = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 21px;

  width: max-content;
  height: fit-content;
  overflow: hidden;
  text-overflow: ellipsis;
  ${LabelListTitle}
  color: var(--gray-800);
  &:hover {
    color: var(--gray-500);
  }
`;

const PostListSection = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px;
  gap: 21px;

  width: 100%;
  height: fit-content;
  overflow: hidden;
  text-overflow: ellipsis;
  ${LabelListTitle}
  color: var(--gray-800);
  &:hover {
    color: var(--gray-500);
  }
`;

const TextGroup = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px;
  gap: 8px;

  width: fit-content;
  height: fit-content;
  overflow: hidden;
  text-overflow: ellipsis;
  ${LabelListTitle}
  color: #fff;
  &:hover {
    color: var(--gray-100);
  }
`;

const Title = styled.div`
  width: ${props => props.width || '326px'};
  height: fit-content;
  overflow: hidden;
  text-overflow: ellipsis;
  ${LabelListTitle}
  color: #fff;
  &:hover {
    color: var(--gray-100);
  }
`;

const SeriesPostNumLayer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 0px;
  gap: 20px;

  padding: 0px;
  gap: 20px;
  ${LabelMedium}
  color: #fff;

  width: fit-content;
  height: fit-content;

  flex: none;
  order: 1;
  align-self: stretch;
  flex-grow: 0;
`;
