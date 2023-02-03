import styled from 'styled-components';
import { LabelListTitle, LabelMedium, ParagraphMedium } from '../../../styles/typo';
import { UserInfoSmall } from '../UserInfo';
import useGetPost from '../../../hooks/useGetPost';

const Container = styled.div`
  box-sizing: border-box;
  width: fit-content;
  height: fit-content;

  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0px;

  border: ${props => (props.selected ? '2px solid var(--gray-400)' : '2px solid #333333')};
  box-shadow: ${props => props.boxShadow || 'none'};
`;

const InfoLayer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 18px 20px;
  width: fit-content;
  height: fit-content;

  background: ${props => (props.selected ? 'var(--gray-600)' : '#efefef')};
  color: ${props => (props.selected ? 'var(--gray-300)' : '')};

  /* Inside auto layout */

  flex: none;
  order: 0;
  align-self: stretch;
  flex-grow: 1;
`;

const ContextLayer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 5px 0px;
  gap: 10px;
  width: 100%;
  height: fit-content;

  /* Inside auto layout */

  flex: none;
  order: 2;
  align-self: stretch;
  flex-grow: 0;
`;

const Box = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 10px 0px;
  gap: 20px;

  width: fit-content;
  height: fit-content;
  ${LabelMedium}
`;

const Box2 = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 20px;

  width: fit-content;
  height: fit-content;
  ${LabelMedium}
`;
const Title = styled.div`
  width: ${props => props.width || '534px'};
  height: 48px;
  overflow: hidden;
  text-overflow: ellipsis;
  text-decoration: none;

  ${LabelListTitle}
`;

const Paragraph = styled.div`
  width: ${props => props.width || '534px'};
  height: 42px;
  overflow: hidden;
  text-overflow: ellipsis;
  text-decoration: none;

  ${ParagraphMedium}
`;

const ImageLayer = styled.img`
  width: ${props => props.imgWidth || '280px'};
  height: 180px;

  background: var(--gray-800);
  display: block;
  object-fit: cover;
`;

const Layer = styled.div`
  cursor: pointer;
  &:hover {
    color: var(--gray-600);
  }
`;
const CreatedAtText = styled.div`
  color: var(--gray-400);
`;

const Title2 = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  margin: 10px;
  gap: 10px;
`;

const PostThinCard = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 880px;
  padding-right: 10px;
  background: ${props => (props.selected ? 'var(--gray-700)' : '#efefef')};
  color: ${props => (props.selected ? 'var(--gray-300)' : '')};
`;

const PostSeriesCard = ({ postId, handleClick, selected, idx }) => {
  // 현재는postId와 관계없이 PostDummy에 있는 데이터를 가져옴
  const { post } = useGetPost(postId);

  const { title, createdAt, modifiedAt, writer } = post;

  return (
    <PostThinCard selected={selected} onClick={handleClick}>
      <Box2>
        <Title2>{idx || '1'}</Title2>
        <Title2>{title || 'title'}</Title2>
      </Box2>
      <Box>
        <UserInfoSmall id={writer?.id} name={writer?.nickname} image={writer?.profileUrl} />
        <CreatedAtText> {new Date().toDateString(modifiedAt || createdAt)} </CreatedAtText>
      </Box>
    </PostThinCard>
  );
};
/**
 * 쇼케이스에서 사용하는 이미지 썸네일 molecules
 * @param {string|number} boxShadow - 전체컨테이너의 그림자 효과
 * @param {string} width - text의 길이
 * @returns {JSX.Element} - PostList 개별 항목을 나타내는 컴포넌트
 */
const PostCard = ({ boxShadow, width, post, handleClick, selected }) => {
  const { title, description, createdAt, modifiedAt, writer, thumbnailUrl } = post;

  // isLoading, isLoadingError state에 따라 컴포넌트 변경 예정
  // 나중에 Title,Paragraph조건문을 제거했을 때 렌더링 속도가 어떻게 변하는지 확인해봐야함
  // currentPost 일때 시각적으로 달라지는 부분이 필요할듯
  return (
    <Container boxShadow={boxShadow} selected={selected}>
      <InfoLayer selected={selected}>
        <Layer onClick={handleClick}>
          <Title width={width}>{title || 'No TItle'} </Title>
          <Paragraph width={width}>{description || 'No Description'}</Paragraph>
        </Layer>
        <ContextLayer>
          <Box>
            <UserInfoSmall id={writer?.id} name={writer?.nickname} image={writer?.profileUrl} />
            <CreatedAtText> {new Date().toDateString(modifiedAt || createdAt)} </CreatedAtText>
          </Box>
          <Box>
            <span> viewed {Number(post?.views)}</span>
          </Box>
        </ContextLayer>
      </InfoLayer>
      <ImageLayer src={thumbnailUrl} alt="thumnail" />
    </Container>
  );
};

/**
 * 쇼케이스에서 사용하는 이미지 썸네일 molecules
 * @param {string|number} boxShadow - 전체컨테이너의 그림자 효과
 * @param {string} width - text의 길이
 * @returns {JSX.Element} - PostListStack을 나타내는 컴포넌트
 */
const PostListStack = ({ boxShadow = 'var(--boxShadow-stack)', width = '278px', post, imgWidth = '100px' }) => {
  const { title, description, createdAt, modifiedAt, writer } = post;

  return (
    <Container boxShadow={boxShadow}>
      <InfoLayer>
        <Title width={width}>{title || 'No TItle'} </Title>
        <Paragraph width={width}>{description || 'No Description'}</Paragraph>
        <ContextLayer>
          <Box>
            <UserInfoSmall id={writer?.id} name={writer?.nickname} image={writer?.profileUrl} />
            <CreatedAtText> {new Date().toDateString(modifiedAt || createdAt)} </CreatedAtText>
          </Box>
        </ContextLayer>
      </InfoLayer>
      <ImageLayer width={imgWidth} />
    </Container>
  );
};
export { PostCard, PostSeriesCard, PostListStack };
