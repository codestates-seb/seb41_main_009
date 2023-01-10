import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 144px;
  justify-content: space-between;
  align-items: center;
  background-color: lavenderblush;
`;

const SeriesPostListContainer = () => {
  return (
    <Container>
      <div>
        <div>Post List</div>
        <div>총 포스트 12개</div>
        <button type="button">자세히 보기</button>
      </div>
      <div>
        <div>Post Card</div>
      </div>
    </Container>
  );
};

export default SeriesPostListContainer;
