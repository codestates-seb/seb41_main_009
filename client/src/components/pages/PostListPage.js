import styled from 'styled-components';
import { useParams, useSearchParams } from 'react-router-dom';
import PageHeader from '../organisms/PageHeader';
import Pagination from '../molecules/Pagination';
import { Lnb } from '../organisms/Lnb';
import PostListContainer from '../organisms/listContainter/PostListContainer';
import { BlueShadowButton } from '../atoms/Buttons';
import useGetPostList from '../../hooks/useGetPostList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
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

  const { postList, postPageInfo } = useGetPostList(category || 'All', curPage);

  return (
    <Container>
      <PageHeader
        headerSubTitle="Intorest In Category"
        headerTitle={`Posts In ${category || 'All'}`}
        asideHeader={<CreatePostButton to="/posts/new">Create Post</CreatePostButton>}
      />
      <Lnb currentTab="Posts" category={category} />

      <PostListContainer postList={postList} />
      <Pagination totalPages={postPageInfo?.totalPages || 1} />
    </Container>
  );
};

export default PostListPage;
