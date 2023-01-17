import styled from 'styled-components';
import Input from '../../atoms/Input';
import { CATEGORIES } from '../../../constants/Categories';
import { HeadingMedium } from '../../../styles/typo';

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

const HeaderSubContainer = styled.div`
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: space-between;
`;

const PostCreateHeader = ({ title, setTitle, curCategory, setCategory }) => {
  return (
    <Container>
      <HeaderTitleContainer>Title</HeaderTitleContainer>
      <HeaderSubContainer>
        <Input
          placeholder="제목을 입력하세요"
          width="80%"
          defaultValue={title}
          height="45px"
          onChange={e => setTitle(e.target.value)}
        />
        <select name="Category" defaultValue={curCategory} onChange={e => setCategory(e.target.value)}>
          <option value="">카테고리를 선택하세요</option>
          {CATEGORIES.map(([category, ...tags]) => {
            return (
              <optgroup key={category[0]} label={category[1]}>
                {tags.map(tag => {
                  return (
                    <option key={tag[1]} value={tag[0]}>
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
