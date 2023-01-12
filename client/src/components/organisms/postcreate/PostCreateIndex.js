import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 50px;
  align-items: center;
  justify-content: space-between;
  background-color: greenyellow;
  margin-bottom: 50px;
`;

const PostCreateIndex = () => {
  return (
    <Container>
      <textarea value="index" />
    </Container>
  );
};

export default PostCreateIndex;
