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
  // ?????????postId??? ???????????? PostDummy??? ?????? ???????????? ?????????
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
 * ?????????????????? ???????????? ????????? ????????? molecules
 * @param {string|number} boxShadow - ????????????????????? ????????? ??????
 * @param {string} width - text??? ??????
 * @returns {JSX.Element} - PostList ?????? ????????? ???????????? ????????????
 */
const PostCard = ({ boxShadow, width, post, handleClick, selected }) => {
  const { title, description, createdAt, modifiedAt, writer, thumbnailUrl } = post;

  // isLoading, isLoadingError state??? ?????? ???????????? ?????? ??????
  // ????????? Title,Paragraph???????????? ???????????? ??? ????????? ????????? ????????? ???????????? ??????????????????
  // currentPost ?????? ??????????????? ???????????? ????????? ????????????
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
 * ?????????????????? ???????????? ????????? ????????? molecules
 * @param {string|number} boxShadow - ????????????????????? ????????? ??????
 * @param {string} width - text??? ??????
 * @returns {JSX.Element} - PostListStack??? ???????????? ????????????
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
