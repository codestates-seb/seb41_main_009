import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

import { PostCard } from '../../molecules/list/PostCard';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1056px;
  gap: 30px;
  margin-top: 50px;
  margin-bottom: 50px;
  justify-content: center;
  align-items: center;
`;

const PostListContainer = ({ postList }) => {
  const navigate = useNavigate();

  return (
    <Container>
      {postList?.map(post => {
        const handleClick = () => {
          navigate(`/posts/${post.category}/${post.id}`);
        };

        return <PostCard key={post.id} post={post} handleClick={handleClick} />;
      })}
    </Container>
  );
};

export default PostListContainer;
