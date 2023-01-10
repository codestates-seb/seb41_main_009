import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 144px;
  margin-bottom: 30px;
  justify-content: space-between;
  align-items: center;
  background-color: royalblue;
`;

const SeriesInfoContainer = () => {
  return (
    <Container>
      <div>
        <div>Series Name</div>
        <div>총 포스트 12개</div>
      </div>
      <div>
        <div>작성자</div>
        <div>
          <button type="button">Edit</button>
        </div>
      </div>
    </Container>
  );
};

export default SeriesInfoContainer;
