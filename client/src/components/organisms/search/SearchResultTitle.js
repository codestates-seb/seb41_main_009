import styled from 'styled-components';

const SearchResultTitle = ({ title, amount, moreBtn }) => {
  return (
    <Container>
      <HeaderContainer>
        <Header>{title}</Header>
        <ResultCount>{amount} 건</ResultCount>
      </HeaderContainer>
      {moreBtn ? <button type="button"> 더보기 </button> : null}
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  width: 100%;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid black;
  padding-bottom: 10px;
  margin-bottom: 15px;
  background-color: yellow;
`;

const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

const Header = styled.div`
  font-size: var(--heading-s);
`;

const ResultCount = styled.div`
  font-size: var(--label-l);
`;

export default SearchResultTitle;
