import styled from 'styled-components';
import { LabelListTitle, LabelMedium } from '../../../styles/typo';

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
  margin-top: 20px;
`;

const HeaderContainer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0px;
  gap: 10px;
`;

const Header = styled.div`
  ${LabelListTitle}
`;

const ResultCount = styled.div`
  ${LabelMedium}
  color: var(--orange-400)
`;

export default SearchResultTitle;
