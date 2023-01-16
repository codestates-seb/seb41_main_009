import styled from 'styled-components';
import Input from '../../atoms/Input';
import { CATEGORIES } from '../../../constants/Categories';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 50px;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 50px;
`;

const PostCreateHeader = () => {
  return (
    <Container>
      <Input placeholder="제목을 입력하세요" width="80%" />
      <select name="Category">
        <option value="카테고리를 선택하세요">카테고리를 선택하세요</option>
        {CATEGORIES.map(([category, ...tags]) => {
          return (
            <optgroup label={category[1]}>
              {tags.map(tag => {
                return <option value={tag[1]}>{tag[1]}</option>;
              })}
            </optgroup>
          );
        })}
      </select>
    </Container>
  );
};

export default PostCreateHeader;
