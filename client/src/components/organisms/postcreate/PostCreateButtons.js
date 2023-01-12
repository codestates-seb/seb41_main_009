import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 50px;
  align-items: center;
  justify-content: flex-end;
  background-color: greenyellow;
`;

const PostCreateButtons = () => {
  return (
    <Container>
      <button type="button">Cancel</button>
      <button type="button">Submit</button>
    </Container>
  );
};

export default PostCreateButtons;
