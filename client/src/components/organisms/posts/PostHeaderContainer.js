import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  width: 1056px;
  height: 193px;
  margin: 0 auto;
  flex-direction: column;
  align-items: flex-start;
  padding: 29px 31px;
  gap: 10px;
  background-color: aqua;
`;

const PostHeaderContainer = () => {
  return (
    <Container>
      <h1>Title</h1>
      <h2>Description</h2>
      <div>작성자 어쩌구저쩌구</div>
    </Container>
  );
};

export default PostHeaderContainer;
