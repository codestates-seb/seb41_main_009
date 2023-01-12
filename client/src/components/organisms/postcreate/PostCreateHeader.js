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
      <input value="Input title" />
      <select name="Category">
        <option value="abc">Category</option>
      </select>
    </Container>
  );
};

export default PostCreateHeader;
