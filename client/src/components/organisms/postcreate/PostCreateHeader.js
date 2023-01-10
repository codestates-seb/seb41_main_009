import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 50px;
  align-items: center;
  justify-content: space-between;
  background-color: gainsboro;
  margin-bottom: 50px;
`;

const PostCreateHeader = () => {
  return (
    <Container>
      <input />
      <select name="Category">
        <option value="abc">abc</option>
      </select>
    </Container>
  );
};

export default PostCreateHeader;
