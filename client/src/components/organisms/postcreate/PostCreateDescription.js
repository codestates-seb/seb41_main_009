import styled from 'styled-components';
import { HeadingMedium } from '../../../styles/typo';
import Input from '../../atoms/Input';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  margin-bottom: 50px;
`;

const HeaderTitleContainer = styled.div`
  ${HeadingMedium}
  margin-bottom: 10px;
`;

const PostCreateDescription = () => {
  return (
    <Container>
      <HeaderTitleContainer>Description</HeaderTitleContainer>
      <Input placeholder="글의 설명을 적어주세요" width="100%" height="45px" />
    </Container>
  );
};

export default PostCreateDescription;
