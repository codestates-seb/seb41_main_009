import styled from 'styled-components';
import Showcasebox from '../organisms/showcase/Showcasebox';
import ShowcaseModal from '../organisms/showcase/ShowcaseModal';

const Showcase = () => {
  return (
    <Container>
      <ShowcaseModal />
      <Header> Header </Header>
      <Body>
        <CaseContainer>
          <Showcasebox
            thumnail="https://images.pexels.com/photos/3558637/pexels-photo-3558637.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
            tagId="1"
            tagName="여행"
            userImg="https://images.pexels.com/photos/3558637/pexels-photo-3558637.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
            userName="여행자"
            summary="푸른 언덕에 배낭을 메고
황금빛 태양 축제를 여는
광야를 향해서 계곡을 향해서
먼 동이 트는 이른 아침에
도시의 소음 수 많은 사람
빌딩 숲 속을 벗어나봐요"
            commentUserName="Hojung"
            commentContent="정말 잘 들었습니다. 화려하네요"
          />
          <Showcasebox />
          <Showcasebox />
        </CaseContainer>
        <CaseContainer>
          <Showcasebox />
          <Showcasebox />
          <Showcasebox />
        </CaseContainer>
        <CaseContainer>
          <Showcasebox />
          <Showcasebox />
          <Showcasebox />
        </CaseContainer>
      </Body>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
  height: 100vh;
`;

const Header = styled.div`
  display: flex;
  width: 100%;
  height: 50px;
  background-color: green;
`;

const Body = styled.div`
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  width: 100%;
  height: auto;
`;

const CaseContainer = styled.div`
  display: flex;
  flex: 1;
  flex-direction: column;
  padding-right: 32px;

  &:last-child {
    padding-right: 0px;
  }
`;

export default Showcase;
