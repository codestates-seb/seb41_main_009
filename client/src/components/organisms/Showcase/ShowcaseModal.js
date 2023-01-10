import styled from 'styled-components';

const ShowcaseModal = ({ isModalOn }) => {
  return (
    <Container isModalOn={isModalOn}>
      <Body>
        <div>
          <Image> Image </Image>
          <Article> Article </Article>
        </div>
        <div>
          <CommentList> CommentList </CommentList>
        </div>
      </Body>
    </Container>
  );
};

export default ShowcaseModal;

const Container = styled.div`
  display: ${props => (props.isModalOn ? 'flex' : 'none')};
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  justify-content: center;
  align-items: center;
  z-index: 99;
  background-color: rgba(0, 0, 0, 0.5);
`;

const Body = styled.div`
  display: flex;
  flex-direction: column;
  width: 800px;
  height: 1000px;
  background-color: white;

  & > div {
    display: flex;
    width: 100%;
    &:first-child {
      height: 500px;
    }
    &:nth-child(2) {
      height: 500px;
    }
  }
`;

const Image = styled.div`
  width: 65%;
  background-color: green;
`;

const Article = styled.div`
  width: 35%;
  background-color: blue;
`;

const CommentList = styled.div`
  width: 100%;
  height: 100%;
  background-color: orange;
`;
