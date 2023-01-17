import styled from 'styled-components';
import { HeadingMedium } from '../../../styles/typo';
import TextEditor from '../../atoms/TextEditor';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 50px;
`;

const HeaderTitleContainer = styled.div`
  ${HeadingMedium}
  margin-bottom: 10px;
`;

const PostCreateBody = () => {
  return (
    <Container>
      <HeaderTitleContainer>Body</HeaderTitleContainer>
      <TextEditor />
    </Container>
  );
};

export default PostCreateBody;
