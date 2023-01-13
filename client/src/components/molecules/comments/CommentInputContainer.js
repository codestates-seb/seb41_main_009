import styled from 'styled-components';
import Input from '../../atoms/Input';
import { OrangeButton } from '../../atoms/Buttons';

const Container = styled.div`
  display: flex;
  width: 100%;
  height: 60px;
  margin: 23px 0;
  justify-content: space-between;
  align-items: center;
`;

const CommentInputContainer = () => {
  return (
    <Container>
      <Input width="85%" height="50px" placeholder="댓글 달기" />
      <OrangeButton width="60px" height="50px">
        Add
      </OrangeButton>
    </Container>
  );
};

export default CommentInputContainer;
