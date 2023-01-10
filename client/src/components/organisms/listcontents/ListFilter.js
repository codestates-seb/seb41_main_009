import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 54px;
  justify-content: space-around;
  align-items: center;
  margin-bottom: 30px;
  background-color: moccasin;
`;

const FilterContainer = () => {
  return (
    <Container>
      <div>
        <span>Post </span>
        <span> Series</span>
      </div>
      <div>
        <button type="button">최신순</button>
        <button type="button">추천순</button>
      </div>
    </Container>
  );
};

export default FilterContainer;
