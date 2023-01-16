import styled from 'styled-components';
import Input from '../../atoms/Input';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 50px;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 50px;
`;

const PostCreateDescription = () => {
  return (
    <Container>
      <Input placeholder="글의 설명을 적어주세요" width="100%" />
    </Container>
  );
};

export default PostCreateDescription;
