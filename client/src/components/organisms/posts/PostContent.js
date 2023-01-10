import styled from 'styled-components';

const Container = styled.div`
  box-sizing: border-box;
  display: flex;
  width: 874px;
  height: 500px;
  gap: 10px;
  background-color: lightpink;
`;

const PostContent = () => {
  return <Container>가나다라마바사아</Container>;
};

export default PostContent;
