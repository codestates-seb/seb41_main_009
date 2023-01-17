import styled from 'styled-components';
import TextEditor from '../../atoms/TextEditor';

const Container = styled.div`
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: center;
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
