import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 300px;
  align-items: center;
  justify-content: space-between;
  background-color: rebeccapurple;
  margin-bottom: 50px;
`;

const PostCreateBody = () => {
  return (
    <Container>
      <textarea value="Editor" />
    </Container>
  );
};

export default PostCreateBody;
