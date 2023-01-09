import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 80px;
  background-color: lightcoral;
  border: 1px solid black;
`;

const Post = () => {
  return (
    <Container>
      <div>Post</div>
    </Container>
  );
};

export default Post;
