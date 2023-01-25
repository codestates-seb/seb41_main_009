import styled from 'styled-components';
import { useParams, useSearchParams } from 'react-router-dom';
import PageHeader from '../organisms/PageHeader';
import Pagination from '../molecules/Pagination';
import Lnb from '../organisms/Lnb';
import PostListContainer from '../organisms/listContainter/PostListContainer';
import { BlueShadowButton } from '../atoms/Buttons';
import useGetPostList from '../../hooks/useGetPostList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1060px;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
  margin-bottom: 100px;
`;

const CreatePostButton = styled(BlueShadowButton)`
  width: 170px;
  height: 44px;
`;
const PostListPage = () => {
  const { category } = useParams();
  const [searchParams] = useSearchParams();
  const curPage = searchParams.get('page');

  const { postList, postPageInfo, isLoading, isLoadingError } = useGetPostList(category, curPage);

  // isLoading, isLoadingError 값에 따라 Loading 컴포넌트 변경 예정
  console.log(isLoading, isLoadingError);

  return (
    <Container>
      <PageHeader
        headerSubTitle="Intorest In Category"
        headerTitle={`Posts In ${category}`}
        asideHeader={<CreatePostButton to="/posts/new">Create Post</CreatePostButton>}
      />
      <Lnb />
      <PostListContainer postList={postList} />
      <Pagination totalPages={postPageInfo.totalPage} />
    </Container>
  );
};

export default PostListPage;
