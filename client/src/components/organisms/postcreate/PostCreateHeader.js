import styled from 'styled-components';
import Input from '../../atoms/Input';
import { CATEGORIES } from '../../../constants/Categories';
import { DisplayXSmall } from '../../../styles/typo';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  margin-bottom: 50px;
`;

const HeaderTitleContainer = styled.div`
  ${DisplayXSmall}
`;

const HeaderSubContainer = styled.div`
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: space-between;
`;

const PostCreateHeader = () => {
  return (
    <Container>
      <HeaderTitleContainer>Title</HeaderTitleContainer>
      <HeaderSubContainer>
        <Input placeholder="제목을 입력하세요" width="80%" height="45px" />
        <select name="Category">
          <option value="카테고리를 선택하세요">카테고리를 선택하세요</option>
          {CATEGORIES.map(([category, ...tags]) => {
            return (
              <optgroup key={category[1]} label={category[1]}>
                {tags.map(tag => {
                  return (
                    <option key={tag[1]} value={tag[1]}>
                      {tag[1]}
                    </option>
                  );
                })}
              </optgroup>
            );
          })}
        </select>
      </HeaderSubContainer>
    </Container>
  );
};

export default PostCreateHeader;
