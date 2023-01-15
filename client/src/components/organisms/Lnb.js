import styled from 'styled-components';
import { LabelLarge } from '../../styles/typo';

const Lnb = ({ currentTab, handleTab }) => {
  const tabList = [{ title: 'All' }, { title: 'Post' }, { title: 'Series' }];

  return (
    <Container>
      <LeftButtonList>
        {tabList.map((el, idx) =>
          currentTab === idx ? (
            <TabButton key={el.title} clicked onClick={() => handleTab(idx)}>
              {el.title}
            </TabButton>
          ) : (
            <TabButton key={el.title} onClick={() => handleTab(idx)}>
              {el.title}
            </TabButton>
          ),
        )}
      </LeftButtonList>
      <Filter>Filter</Filter>
    </Container>
  );
};

const Container = styled.div`
  ${LabelLarge};
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin: 15px 91px;
  height: auto;
`;

const LeftButtonList = styled.div`
  display: flex;
  gap: 35px;
`;

const Filter = styled.button`
  all: unset;
  color: var(--gray-900);
  cursor: pointer;
`;

const TabButton = styled.button`
  all: unset;
  color: ${props => (props.clicked ? 'var(--gray-900)' : 'var(--gray-500)')};
  cursor: pointer;
`;

export default Lnb;
