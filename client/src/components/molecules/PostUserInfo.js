import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 46px;
  justify-content: space-between;
  align-items: center;
`;

const PostUserInfo = () => {
  return (
    <Container>
      <div>작성자 어쩌구저쩌구</div>
      <div>
        <button type="button">버튼</button>
        <button type="button">버튼</button>
      </div>
    </Container>
  );
};

export default PostUserInfo;
