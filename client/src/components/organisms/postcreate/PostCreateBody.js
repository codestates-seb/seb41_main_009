import styled from 'styled-components';
import TextEditor from '../../atoms/TextEditor';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 300px;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 50px;
`;

const PostCreateBody = () => {
  return (
    <Container>
      <TextEditor />
    </Container>
  );
};

export default PostCreateBody;
