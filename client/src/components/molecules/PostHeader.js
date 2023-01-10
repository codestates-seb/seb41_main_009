import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 994px;
  height: 80px;
  margin-bottom: 13px;
`;

const PostHeader = () => {
  return (
    <Container>
      <h1>Title</h1>
      <h2>Description</h2>
    </Container>
  );
};

export default PostHeader;
